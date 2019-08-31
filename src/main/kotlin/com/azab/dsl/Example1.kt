package com.azab.dsl

class Node(var value: String? = null, var next: Node? = null)

fun main(args: Array<String>) {

    val head1 = node {
        value = "node1"
        next = node {
            value = "node2"
            next = node {
                value = "node3"
                next = null
            }
        }
    }

    val head2 = node {
        value = "node1"
        next = node("node2") {

        }
    }

    val head3 = node {
        value = "node1"
        next = "node2".toNode()
    }

    val head4 = node {
        value = "node1"
        next = "node2".toNode().apply {
            next = "node3".toNode()
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