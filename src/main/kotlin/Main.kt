package at.fhv

import at.fhv.objects.AVLTreeNode
import at.fhv.objects.BinaryTreeNode
import at.fhv.objects.TreeNode
import at.fhv.objects.TreePrinter.printTree

fun main() {
    var root: TreeNode? = null
    println("Select an option:")
    println("1: Binary Tree")
    println("2: AVL Tree")
    println("q: Quit")

    val tree = when (readlnOrNull() ?: "") {
        "1" -> 1
        "2" -> 2
        else -> return
    }


    println("Enter a command Followed by a number (e.g. `in20`:")
    println("in: Insert")
    println("rm: Remove")
    println("se: Search")
    println("pr: Predecessor")
    println("su: Successor")
    println("------------------")
    println("Full Commands without numbers at the end")
    println("p: Print tree")
    println("pl: Print tree as List")
    println("min: Min")
    println("max: Max")
    println("c: Clear")
    println("q: Quit")

    while (true) {
        print("> ")
        with(readlnOrNull() ?: "") {
            when {
                this == "p" -> root?.printTree()
                this == "pl" -> root?.printList()
                this == "min" -> println("Min: ${root?.min()?.value}")
                this == "max" -> println("Max: ${root?.max()?.value}")
                this == "c" -> root = null
                this == "q" -> return

                else -> try {
                    val number = this.substring(2).toInt()

                    when {
                        this.startsWith("in") -> root = root?.insert(number) ?: newTree(number, tree)
                        this.startsWith("rm") -> root = root?.remove(number) ?: run {
                            println("Value not found.")
                            null
                        }

                        this.startsWith("se") -> root?.search(number)?.let {
                            println("Value found.")
                        } ?: run {
                            println("Value not found.")
                        }


                        this.startsWith("pr") -> println("Predecessor: ${root?.predecessor(number)?.value}")
                        this.startsWith("su") -> println("Successor: ${root?.successor(number)?.value}")

                        else -> println("Invalid command.")
                    }

                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a number.")
                }
            }
        }
    }
}

private fun newTree(value: Int, tree: Int): TreeNode {
    return when (tree) {
        1 -> BinaryTreeNode(value)
        2 -> AVLTreeNode(value)
        else -> throw IllegalArgumentException("Invalid tree type.")
    }
}