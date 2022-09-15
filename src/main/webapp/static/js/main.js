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
        cardHeaderText.classList.add("price");
        cardHeaderText.classList.add("desc");

        image.src = "/static/img/product_1.jpg";
        cardTitle.textContent = product.name;
        cardHeaderText.textContent = product.description;
        cardBodyText.textContent = product.defaultPrice;
        cartButton.href = "#";
        cartButton.textContent = "Add to cart";

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
