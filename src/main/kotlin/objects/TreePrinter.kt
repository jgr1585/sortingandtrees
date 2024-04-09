package at.fhv.objects

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.pow

internal object TreePrinter {
    fun TreeNode.printTree() {
        val maxLevel = maxLevel(this)

        printNodeInternal(Collections.singletonList(this), 1, maxLevel)
    }

    private fun printNodeInternal(nodes: List<TreeNode?>, level: Int, maxLevel: Int) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return

        val floor = maxLevel - level
        val endgeLines = 2.0.pow((max((floor - 1).toDouble(), 0.0))).toInt()
        val firstSpaces = 2.0.pow(floor.toDouble()).toInt() - 1
        val betweenSpaces = 2.0.pow((floor + 1).toDouble()).toInt() - 1

        printWhitespaces(firstSpaces)

        val newNodes: MutableList<TreeNode?> = ArrayList()
        for (node in nodes) {
            if (node != null) {
                print(node.value)
                newNodes.add(node.left)
                newNodes.add(node.right)
            } else {
                newNodes.add(null)
                newNodes.add(null)
                print(" ")
            }

            printWhitespaces(betweenSpaces)
        }
        println("")

        for (i in 1..endgeLines) {
            for (j in nodes.indices) {
                printWhitespaces(firstSpaces - i)
                if (nodes[j] == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1)
                    continue
                }

                if (nodes[j]?.left != null) print("/")
                else printWhitespaces(1)

                printWhitespaces(i + i - 1)

                if (nodes[j]?.right != null) print("\\")
                else printWhitespaces(1)

                printWhitespaces(endgeLines + endgeLines - i)
            }

            println("")
        }

        printNodeInternal(newNodes, level + 1, maxLevel)
    }

    private fun printWhitespaces(count: Int) {
        for (i in 0 until count) print(" ")
    }

    private fun maxLevel(node: TreeNode?): Int {
        if (node == null) return 0

        return (max(
            maxLevel(node.left).toDouble(),
            maxLevel(node.right).toDouble()
        ) + 1).toInt()
    }

    private fun <T> isAllElementsNull(list: List<T?>): Boolean {
        for (`object` in list) {
            if (`object` != null) return false
        }

        return true
    }
}