package at.fhv.objects

class AVLTreeNode(
    override val value: Int,
): TreeNode {
    override var left: AVLTreeNode? = null
    override var right: AVLTreeNode? = null

    private constructor(value: Int, left: AVLTreeNode?, right: AVLTreeNode?): this(value) {
        this.left = left
        this.right = right
    }

    override fun insert(value: Int): AVLTreeNode =
        _insert(value).balance().node

    private fun _insert(value: Int): AVLTreeNode {
        if (value < this.value) {
            left?._insert(value) ?: run { left = AVLTreeNode(value) }
        } else {
            right?._insert(value) ?: run { right = AVLTreeNode(value) }
        }

        return this
    }

    override fun remove(value: Int): AVLTreeNode? =
        _remove(value)?.balance()?.node

    private fun _remove(value: Int): AVLTreeNode? {
        if (value < this.value) {
            left = left?.remove(value)
        } else {
            right = right?.remove(value)
        }

        if (value == this.value) {
            return when {
                left == null -> right
                right == null -> left
                else -> {
                    val successor = right?.min()!!
                    AVLTreeNode(
                        successor.value,
                        left,
                        right?._remove(successor.value)
                    )
                }
            }
        }

        return this
    }

    private fun balance(): BalanceResult {
        val balanceLeft = left?.balance() ?: BalanceResult(this)
        val balanceRight = right?.balance() ?: BalanceResult(this)

        val balanceFactor = balanceLeft.height - balanceRight.height

        val root = when {
            balanceFactor > 1 -> {
                if (balanceLeft.heightLeft >= balanceLeft.heightRight) {
                    rightRotate(balanceLeft.node, balanceRight.node)
                } else {
                    leftRotate(balanceLeft.node, balanceRight.node).rightRotate(balanceLeft.node, balanceRight.node)
                }
            }
            balanceFactor < -1 -> {
                if (balanceRight.heightRight >= balanceRight.heightLeft) {
                    leftRotate(balanceLeft.node, balanceRight.node)
                } else {
                    rightRotate(balanceLeft.node, balanceRight.node).leftRotate(balanceLeft.node, balanceRight.node)
                }
            }

            else -> this
        }

        return BalanceResult(
            root,
            balanceLeft.height + 1,
            balanceRight.height + 1
        )
    }

    override fun toString(): String {
        return "AVLTreeNode(value=$value)"
    }

    private fun leftRotate(left: AVLTreeNode, right: AVLTreeNode): AVLTreeNode {
        val newRoot = right
        val newLeft = AVLTreeNode(value, left.left, newRoot.left)
        return AVLTreeNode(right.value, newLeft, newRoot.right)
    }

    private fun rightRotate(left: AVLTreeNode, right: AVLTreeNode): AVLTreeNode {
        val newRoot = left
        val newRight = AVLTreeNode(value, newRoot.right, right.right)
        return AVLTreeNode(left.value, newRoot.left, newRight)
    }

    private class BalanceResult(
        val node: AVLTreeNode,
        val heightLeft: Int = 0,
        val heightRight: Int = 0,
    ) {
        val height: Int = maxOf(heightLeft, heightRight)

        override fun toString(): String {
            return "BalanceResult(node=$node, height=$height)"
        }
    }
}