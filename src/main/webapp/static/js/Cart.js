let addToCartButtons = document.querySelectorAll(".add-to-cart")
let cartButton = document.querySelector(".cart")

async function addProduct(productId) {
    let response = await fetch(`/api/addToCart?product-id=${productId}`)
    return response.json()
}


addToCartButtons.forEach(button => button.addEventListener('click', async function (){
    let id = button.id
    let totalProduct = await addProduct(id)
    cartButton.textContent=`cart ${totalProduct}`
}))
