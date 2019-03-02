package edu.gatech.cs2340.spacetraders.entity


import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * MarketPlace class that provides all services related to buying/selling and stocking Inventories
 *
 * @param planetInventory the Inventory of the current Solar System the player is located in
 * @param techLevel the TechLevel of the current Solar System the player is located in
 * @param resources the Resources level of the current Solar System the player is located in
 * @param randomEvent the randomEvent that is currently occurring in the Solar System
 */
class MarketPlace(var planetInventory : Inventory, var techLevel : TechLevels, var resources : Resources,
                  var randomEvent : RandomEvent = RandomEvent.NOEVENT) {

    var productArray : Array<Products> = Products.values()
    var priceMap : HashMap<Products, Int> = HashMap()

    /**
     * Stocks the current Solar System's Inventory with products based on the current Solar System's tech level and resource level
     */
    fun stockInventory() {
        val initialStockAmount : Int = this.getTotalAmountOfBuyableProducts()
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
        planetInventory.capacity = this.getTotalAmountOfBuyableProducts()
    }

    /**
     * Creates the priceMap for the marketPlace
     *
     * @param player the player of the current game instance
     */
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

    /**
     * Getter for a set of entries, where each entry is a (product, quantity) pair that corresponds to a
     * buyable product and its inventory quantity
     *
     * @return a set of entries, where each entry is a (product, quantity) pair that corresponds to a buyable product
     * and its inventory quantity
     */
    fun getBuyableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        var buyableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = planetInventory.getProductSet()
        for (entry in buyableSet) {
            if (techLevel.level < entry.key.MTLP.level) {
                buyableSet.remove(entry)
            }
        }
        return buyableSet
    }

    /**
     * Getter for a set of entries, where each entry is a (product, quantity) pair that corresponds to a
     * sellable product and its inventory quantity
     *
     * @return a set of entries, where each entry is a (product, quantity) pair that corresponds to a sellable product
     * and its inventory quantity
     */
    fun getSellableProducts(playerInventory : Inventory) : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        var sellableSet : MutableSet<MutableMap.MutableEntry<Products, Int>> = playerInventory.getProductSet()
        for (entry in sellableSet) {
            if (techLevel.level < entry.key.MTLU.level) {
                sellableSet.remove(entry)
            }
        }
        return sellableSet
    }

    /**
     * Getter for total amount of products you can buy from the Solar System
     *
     * @return the total amount of products you can buy from the Solar System
     */
    fun getTotalAmountOfBuyableProducts() : Int {
        val buyableSet : Set<MutableMap.MutableEntry<Products, Int>> = this.getBuyableProducts()
        return buyableSet.size
    }

    /**
     * Calculates the price for a product given the following economic model :
     * (base + (IPL*levelDifference) + variance) * randomeEventMultiplier * crMultiplier * erMultiplier
     *
     * @param product the product to calculate the price for
     * @return the price of the product
     */
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

    /**
     * Process a "buy" transaction between player and the current Solar System
     *
     * @param player the player buying the products
     * @param product the product the player is buying
     * @param quantity the amount of the product the player is buying
     * @return an Int indicating the success of the operation: 0 = success, 1 = "Not enough cargo capacity", 2 = "Not enough credits"
     */
    fun buy(player : Player, product : Products, quantity : Int) : Int {
        if (player.getTotalAmountInInventory() + quantity > player.getShipCargoCapacity()) {
            return  1// May throw an exception or return an Int to indicate not enough cargo capacity
        } else {
            if (player.credits - (priceMap[product]!! * quantity) < 0) {
            return 2// May throw an exception or return an Int to indicate not enough credits
            } else {
                planetInventory.remove(product, quantity)
                player.addToInventory(product, quantity)
                player.credits = player.credits - (priceMap[product]!! * quantity)
                return 0
            }
        }
    }

    /**
     * Process a "sell" transaction between player and the current Solar System
     *
     * @param player the player selling the products
     * @param product the product the player is selling
     * @param quantity the amount of the product the player is selling
     * @return an Int indicating the success of the operation: 0 = success, 1 = "Not enough of the product owned"
     */
    fun sell(player : Player, product : Products, quantity: Int) : Int {
        if ((player.getAmountOf(product) - quantity) < 0) {
            return 1// May throw an exception or return an Int/Boolean to indicate the player is selling more than they own
        } else {
            player.removeFromInventory(product, quantity)
            planetInventory.add(product, quantity)
            player.credits = player.credits + (priceMap[product]!! * quantity)
            return 0
        }
    }

}