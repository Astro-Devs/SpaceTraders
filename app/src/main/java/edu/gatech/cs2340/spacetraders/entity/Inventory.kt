package edu.gatech.cs2340.spacetraders.entity

/**
 * Inventory class that holds Products and their corresponding quantities
 */
class Inventory {
    var productMap: HashMap<Products, Int> = HashMap()
    var capacity: Int = 0

    /**
     * Adds "quantity" products to Inventory
     *
     * @param product the product to add into Inventory
     * @param quantity the amount of the product to add into Inventory
     */
    fun add(product: Products, quantity: Int) {
        if (!productMap.containsKey(product)) {
            productMap[product] = quantity
        } else {
            productMap[product] = productMap[product]!! + quantity
        }
    }

    /**
     * Removes "quantity" products from Inventory
     *
     * @param product the product to remove from Inventory
     * @param quantity the amount of the product to remove from Inventory
     */
    fun remove(product: Products, quantity: Int) {
        if (productMap[product]!! - quantity <= 0) {
            productMap.remove(product)
        } else {
            productMap[product] = productMap[product]!! - quantity
        }
    }

    /**
     * Getter for the amount of "product" in Inventory
     *
     * @param product the product to get the amount of from the Inventory
     * @return the amount of the product in the Inventory
     */
    fun getAmountOf(product: Products): Int {
        if (!productMap.containsKey(product)) {
            return 0
        } else {
            return productMap[product]!!
        }
    }

    /**
     * Getter for the total amount of all products in Inventory
     *
     * @return the total amount of all products in Inventory
     */
    fun getTotalAmountofProducts(): Int {
        var total = 0
        for (key in productMap.keys) {
            if (key != Products.FUEL) {
                total += this.getAmountOf(key)
            }

        }
        return total
    }

    /**
     * Getter for a set of all products in Inventory
     *
     * @return a set of all products in Inventory
     */
    fun getProductSet(): MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return productMap.entries
    }

    override fun toString(): String {
        var returnString = "Inventory: "
        for ((key, value) in productMap) {
            returnString = returnString + "$value ${key.name}, "
        }
        return returnString
    }
}