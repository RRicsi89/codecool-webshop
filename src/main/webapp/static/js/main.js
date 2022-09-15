async function getProductsByCategory() {
    const categorySelector = document.getElementById("category-selector");
    let response = await apiGet("/categories?name=" + categorySelector.value);
    return await response;
}


async function getProductsBySupplier() {
    const supplierSelector = document.getElementById("supplier-selector");
    const response = await apiGet("/supplier?name=" + supplierSelector.value);
    return await response;
}


async function handleSupplier() {
    const products = await getProductsBySupplier();
    cardBuilder(products);
}


async function handleCategory() {
    const products = await getProductsByCategory();
    cardBuilder(products);
}


function cardBuilder(products) {
    const productsDiv = document.getElementById("products");
    productsDiv.innerHTML = "";
    for(let product of products) {
        const card = elementCreator("div", "card");
        const image = elementCreator("img", "image");
        const cardHeader = elementCreator("div", "card-header");
        const cardTitle = elementCreator("h4", "card-title");
        const cardHeaderText = elementCreator("p", "lead");
        const cardBody = elementCreator("div", "card-body");
        const cardBodyTextCont = elementCreator("div", "card-text");
        const cardBodyText = elementCreator("p", "lead");
        const cartBtnContainer = elementCreator("div", "card-text");
        const cartButton = elementCreator("a", "btn-success");
        cartButton.classList.add("btn");
        cartButton.classList.add("add-to-cart");
        cartButton.id = product.id;
        cardHeaderText.classList.add("price");
        cardHeaderText.classList.add("desc");

        image.src = "/static/img/product_1.jpg";
        cardTitle.textContent = product.name;
        cardHeaderText.textContent = product.description;
        cardBodyText.textContent = product.defaultPrice;
        cartButton.textContent = "Add to cart";

        cartButton.addEventListener("click", async function() {
            const id = cartButton.id
            const cart = document.querySelector(".cart");
            cart.textContent = await addProduct(id);
        })

        card.appendChild(image);
        card.appendChild(cardHeader);
        card.appendChild(cardBody);
        cardHeader.appendChild(cardTitle);
        cardHeader.appendChild(cardHeaderText);
        cardBody.appendChild(cardBodyTextCont);
        cardBody.appendChild(cartBtnContainer);
        cardBody.appendChild(cartBtnContainer);
        cartBtnContainer.appendChild(cartButton);
        cardBodyTextCont.appendChild(cardBodyText);
        productsDiv.appendChild(card);
    }
}


function elementCreator(type, className) {
    const element = document.createElement(type);
    element.classList.add(className);
    return element;
}


async function apiGet(url) {
    let response = await fetch(url, {method: "GET"});
    if(response.status === 200) {
        return response.json();
    }
}
