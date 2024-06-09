package at.fhv.objects

class AVLTreeNode(
    override val value: Int,
): TreeNode {
    private var leftNode: AVLNodeDetails? = null
    private var rightNode: AVLNodeDetails? = null

    override val left: AVLTreeNode?
        get() = leftNode?.node
    override val right: AVLTreeNode?
        get() = rightNode?.node

    private val height: Int
        get() = maxOf(leftNode?.height ?: 0, rightNode?.height ?: 0) + 1

    private constructor(value: Int, left: AVLNodeDetails?, right: AVLNodeDetails?): this(value) {
        this.leftNode = left
        this.rightNode = right
    }

    override fun insert(value: Int): AVLTreeNode =
        _insert(value).balance()

    @Suppress("kotlin:S100", "FunctionName")
    private fun _insert(value: Int): AVLTreeNode {
        if (value < this.value) {
            left?._insert(value) ?: run { leftNode = AVLNodeDetails(AVLTreeNode(value), 1)}
        } else {
            right?._insert(value) ?: run { rightNode = AVLNodeDetails(AVLTreeNode(value), 1)}
        }

        return this
    }

    override fun remove(value: Int): AVLTreeNode =
        throw NotImplementedError("Remove is not implemented for AVL trees.")

    private fun balance(): AVLTreeNode {
        val leftNB = left?.balance()
        val rightNB = right?.balance()

        val balanceFactor = (leftNB?.height ?: 0) - (rightNB?.height ?: 0)

        val leftN = leftNB ?: AVLTreeNode(value)
        val rightN = rightNB ?: AVLTreeNode(value)

        return when {
            balanceFactor > 1 -> {
                if ((leftNB?.leftNode?.height ?: 0) >= (leftNB?.rightNode?.height ?: 0)) {
                    rightRotate(leftN, rightN)
                } else {
                    leftRotate(leftN, rightN).rightRotate(leftN, rightN)
                }
            }
            balanceFactor < -1 -> {
                if ((rightNB?.rightNode?.height ?: 0) >= (rightNB?.leftNode?.height ?: 0)) {
                    leftRotate(leftN, rightN)
                } else {
                    rightRotate(leftN, rightN).leftRotate(leftN, rightN)
                }
            }

            else -> {
                val left = if (leftNB != null ) AVLNodeDetails(leftNB, leftNB.height) else null
                val right = if (rightNB != null) AVLNodeDetails(rightNB, rightNB.height) else null

                AVLTreeNode(value, left, right)
            }
        }
    }

    private fun leftRotate(left: AVLTreeNode, right: AVLTreeNode): AVLTreeNode {
        val newRoot = right
        val newLeft = AVLTreeNode(value, left.leftNode, newRoot.leftNode)
        return AVLTreeNode(
            right.value,
            AVLNodeDetails(newLeft, newLeft.height),
            newRoot.rightNode)
    }

    private fun rightRotate(left: AVLTreeNode, right: AVLTreeNode): AVLTreeNode {
        val newRoot = left
        val newRight = AVLTreeNode(value, newRoot.rightNode, right.rightNode)
        return AVLTreeNode(
            left.value,
            newRoot.leftNode,
            AVLNodeDetails(newRight, newRight.height)
        )
    }

    override fun toString(): String {
        return "AVLTreeNode(value=$value)"
    }

    private class AVLNodeDetails(
        val node: AVLTreeNode,
        val height: Int,
    )
}