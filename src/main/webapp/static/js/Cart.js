let addToCartButtons = document.querySelectorAll(".add-to-cart")
let cartButton = document.querySelector(".cart")
let editQuantityButton = document.querySelectorAll(".edit-quantity")


async function addProduct(productId) {
    let response = await fetch(`/api/addToCart?product-id=${productId}`)
    return response.json()
}


async function ChangeCart(productId) {
    let response = await fetch(`/api/changeCart?product-id=${productId}`)
    return response.json()
}


editQuantityButton.forEach(button => button.addEventListener('click', async function () {
    let id = button.id
    let data = await ChangeCart(id)
    if (data[0] === 0) {
        document.getElementsByName(Math.abs(id))[0].remove()
    } else {
        document.getElementsByName(Math.abs(id))[1].textContent = data[1]
        document.getElementsByName(Math.abs(id))[2].textContent = data[0]
    }
    document.getElementById("checkout-btn").textContent = `Checkout ${data[2]}$`
}))


addToCartButtons.forEach(button => button.addEventListener('click', async function (){
    let id = button.id
    cartButton.textContent = await addProduct(id)
}))


async function setCartButton() {
    cartButton.textContent = await addProduct(0);
}


if(cartButton !== null) {
    setCartButton()
}

