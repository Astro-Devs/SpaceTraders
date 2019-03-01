package edu.gatech.cs2340.spacetraders.entity


import kotlin.math.roundToInt
import kotlin.random.Random

class MarketPlace(var planetInventory : Inventory, var techLevel : TechLevels, var resources : Resources,
                  var randomEvent : RandomEvent = RandomEvent.NOEVENT) {

    var productArray : Array<Products> = Products.values()
    var priceMap : HashMap<Products, Int> = HashMap()

    fun stockInventory() {
        val initialStockAmount : Int = planetInventory.getTotalAmountofProducts()
        for (currentProduct : Products in productArray) {
            if (techLevel.level >= currentProduct.MTLP.level) {
                if (initialStockAmount <= planetInventory.capacity/2) {
                    if (techLevel.equals(currentProduct.TTP)) {
                        planetInventory.add(currentProduct, Random.nextInt(6, 10))
                    } else {
                        planetInventory.add(currentProduct, Random.nextInt(1, 6))
                    }

                } else {
                    if (techLevel.equals(currentProduct.TTP)) {
                        planetInventory.add(currentProduct, Random.nextInt(3, 5))
                    } else {
                        planetInventory.add(currentProduct, Random.nextInt(0, 3))
                    }
                }
            }
        }
        planetInventory.capacity = planetInventory.getTotalAmountofProducts()
    }

    fun initializePrices(player : Player) {
        val buyableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = this.getBuyableProducts()
        val sellableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = this.getSellableProducts(player.playerInventory)
        for (entry in buyableSet) {
            priceMap[entry.key] = this.calculatePrice(entry.key)
        }
        for (entry in sellableSet) {
            priceMap[entry.key] = this.calculatePrice(entry.key)
        }
    }

    fun getBuyableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        var buyableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = planetInventory.getProductSet()
        for (entry in buyableSet) {
            if (techLevel.level < entry.key.MTLP.level) {
                buyableSet.remove(entry)
            }
        }
        return buyableSet
    }

    fun getSellableProducts(playerInventory : Inventory) : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        var sellableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = playerInventory.getProductSet()
        for (entry in sellableSet) {
            if (techLevel.level < entry.key.MTLU.level) {
                sellableSet.remove(entry)
            }
        }
        return sellableSet
    }

    fun calculatePrice(product : Products) : Int {
        val base : Int = product.BASEPRICE
        val IPL : Int = product.IPL
        val levelDifference : Int = techLevel.level - product.MTLP.level
        val variance : Int = Random.nextInt(-1 * product.Var, product.Var + 1)
        var randomEventMutliplier = 1.0f
        var crMultiplier = 1.0f
        var erMultiplier = 1.0f

        if (randomEvent.equals(product.RE)) {
            randomEventMutliplier = 1.5f
        }

        if (resources.equals(product.CR)) {
            crMultiplier = .75f
        }

        if (resources.equals(product.ER)) {
            erMultiplier = 1.25f
        }

        val result : Float = (base + (IPL * levelDifference) + variance) * randomEventMutliplier * crMultiplier * erMultiplier

        return result.roundToInt()
    }

    fun buy(player : Player, product : Products, quantity : Int) {
        if (player.getTotalAmountInInventory() + quantity > player.getShipCargoCapacity()) {
            return  // May throw an exception or return an Int to indicate not enough cargo capacity
        } else {
            if (player.credits - (priceMap[product]!! * quantity) < 0) {
            return // May throw an exception or return an Int to indicate not enough credits
            } else {
                planetInventory.remove(product, quantity)
                player.addToInventory(product, quantity)
                player.credits = player.credits - (priceMap[product]!! * quantity)
            }
        }
    }

    fun sell(player : Player, product : Products, quantity: Int) {
        if ((player.getAmountOf(product) - quantity) < 0) {
            return // May throw an exception or return an Int/Boolean to indicate the player is selling more than they own
        } else {
            player.removeFromInventory(product, quantity)
            planetInventory.add(product, quantity)
            player.credits = player.credits + (priceMap[product]!! * quantity)
        }
    }

}