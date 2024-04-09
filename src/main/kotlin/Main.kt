package at.fhv

import at.fhv.objects.AVLTreeNode
import at.fhv.objects.BinaryTreeNode
import at.fhv.objects.TreePrinter.printTree

fun main() {
    var root: AVLTreeNode? = null

    println("Enter a number to insert into the tree or 'q' to quit:")

    while (true) {
        val input = readlnOrNull() ?: ""
        if (input == "q") {
            break
        }
        try {
            root = root?.insert(input.toInt()) ?: AVLTreeNode(input.toInt())

            root.printTree()
            root.printList()
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number.")
        }
    }
}