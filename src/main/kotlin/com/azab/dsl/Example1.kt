package com.azab.dsl

class Node(val value: String? = null, val node: Node? = null)

class NodeBuilder(var value: String? = null, var node: Node? = null) {
    fun build() : Node = Node(value, node)
}

fun node(block: NodeBuilder.() -> Unit): Node {
    return NodeBuilder().apply(block).build()
}

fun node(value: String, block: NodeBuilder.() -> Unit): Node {
    return NodeBuilder(value = value).apply(block).build()
}

fun String.toNode(): Node {
    return Node(this)
}

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
        node = node("node2") {}
    }

    val head3 = node {
        value = "node1"
        node = "node2".toNode()
    }

}

