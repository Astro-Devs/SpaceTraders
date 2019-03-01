package edu.gatech.cs2340.spacetraders.entity

class Inventory {
    var productMap: HashMap<Products, Int> = HashMap()
    var capacity : Int = 0

    fun add(product: Products, quantity: Int) {
        if (!productMap.containsKey(product)) {
            productMap[product] = quantity
        } else {
            productMap[product] = productMap[product]!! + quantity
        }
    }

    fun remove(product: Products, quantity: Int) {
        if (productMap[product]!! - quantity <= 0) {
            productMap.remove(product)
        } else {
            productMap[product] = productMap[product]!! - quantity
        }
    }

    fun getAmountOf(product: Products): Int {
        if (!productMap.containsKey(product)) {
            return 0
        } else {
            return productMap[product]!!
        }
    }

    fun getTotalAmountofProducts() : Int {
        var total = 0
        for (key in productMap.keys) {
            total += this.getAmountOf(key)
        }
        return total
    }

    fun getProductSet() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return productMap.entries
    }

    override fun toString() : String {
        var returnString = "Inventory: "
        for ((key, value) in productMap) {
            returnString = returnString + "$value ${key.name}, "
        }
        return returnString
    }
}