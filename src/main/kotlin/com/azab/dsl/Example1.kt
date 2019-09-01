package com.azab.dsl

class Node(var value: String? = null, var node: Node? = null)

fun main(args: Array<String>) {

    val head1 = node {
        value = "node1"
        node = node {
            value = "node2"
            node = node {
                value = "node3"
                node = null
            }
        }
    }

    val head2 = node {
        value = "node1"
        node = node("node2") {

        }
    }

    val head3 = node {
        value = "node1"
        node = "node2".toNode()
    }

    val head4 = node {
        value = "node1"
        node = "node2".toNode().apply {
            node = "node3".toNode()
        }
    }

}

fun node(block: Node.() -> Unit): Node {
    return Node().apply(block)
}

fun node(value: String, block: Node.() -> Unit): Node {
    return Node(value = value).apply(block)
}

fun String.toNode(): Node {
    return Node(this)
}
