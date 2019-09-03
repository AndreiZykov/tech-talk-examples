package com.azab.dsl

class Person(val name: String? = null, val address: Address? = null, val friends: List<Person>? = null)

class Address(val line1: String? = null, val line2: String? = null, val city: String? = null, val zip: String? = null)

class PersonBuilder(
    var name: String? = null,
    var address: Address? = null,
    private var friends: MutableList<Person> = mutableListOf()) {
    fun build() = Person(name, address, friends)
    fun address(block: AddressBuilder.() -> Unit)  {
        address = AddressBuilder().apply(block).build()
    }
    fun friend(block: PersonBuilder.() -> Unit) {
        friends.add(PersonBuilder().apply(block).build())
    }
}

class AddressBuilder(
    var line1: String? = null,
    var line2: String? = null,
    var city: String? = null,
    var zip: String? = null
) {
    fun build() = Address(line1, line2, city, zip)
}


fun person(block: PersonBuilder.() -> Unit): Person {
    val p = PersonBuilder()
    p.block()
    return p.build()
}

fun main() {


    val p1 = person {
        name = "Mike"
        address {
            line1 = "65 Broadway"
            line2 = "Suit 23"
            city = "New York"
            zip = "10003"
        }
        friend {
            name = "Ike"
        }
    }

    val p2 = person {
        name = "Mike"
        address { "Mike's address" }
        friend {
            name = "Ike"
            address { "Ike's address" }
        }

        friend {
            name = "Ike"
            address { "Ike's address" }
        }
    }

}