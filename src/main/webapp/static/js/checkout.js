function createCheckoutForm() {

    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal");

    const modalHeader = document.createElement("div");
    modalHeader.innerText = "User information";
    modalHeader.classList.add("modal-header");

    const modalFooter = document.createElement("div");
    modalFooter.classList.add("modal-footer");
    const submitBtn = document.createElement("button");
    submitBtn.classList.add("submit-btn");
    submitBtn.innerText = "Go to Payment";
    submitBtn.type = "submit";
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancel-btn");
    cancelBtn.innerText = "Cancel";
    modalFooter.appendChild(submitBtn);
    modalFooter.appendChild(cancelBtn);

    let modalBody = createModalBody();

    submitBtn.setAttribute("form", "checkout-form");

    modalContainer.appendChild(modalHeader);
    modalContainer.appendChild(modalBody);
    modalContainer.appendChild(modalFooter);

    return modalContainer;
}

function createModalBody() {
    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalBody.innerHTML =
        "<form id='checkout-form' action='/api/user-validation' method='POST'>" +
        "<label for='username'>Name</label></br>" +
        "<input type='text' name='username' id='username' required></br></br>" +

        "<label for='email'>Email</label></br>" +
        "<input type='text' name='email' id='email' required></br></br>" +

        "<label for='phone'>Phone number</label></br>" +
        "<input type='number' name='phone' id='phone' required></br></br>" +

        "<label>Billing Address</label></br></br>" +
        "<label for='b-country'>CCV coder</label></br>" +
        "<input type='text' name='b-country' id='b-country' required></br></br>" +
        "<label for='b-city'>CCV coder</label></br>" +
        "<input type='text' name='b-city' id='b-city' required></br></br>" +
        "<label for='b-zipcode'>CCV coder</label></br>" +
        "<input type='number' name='b-zipcode' id='b-zipcode' required></br></br>" +
        "<label for='b-address'>CCV coder</label></br>" +
        "<input type='text' name='b-address' id='b-address' required></br></br>" +

        "<label>Shipping Address(optional)</label></br></br>" +
        "<label for='sh-country'>CCV coder</label></br>" +
        "<input type='text' name='sh-country' id='sh-country'></br></br>" +
        "<label for='sh-city'>CCV coder</label></br>" +
        "<input type='text' name='sh-city' id='sh-city'></br></br>" +
        "<label for='sh-zipcode'>CCV coder</label></br>" +
        "<input type='number' name='sh-zipcode' id='sh-zipcode'></br></br>" +
        "<label for='sh-address'>CCV coder</label></br>" +
        "<input type='text' name='sh-address' id='sh-address'></br></br>" +
        "</form>"
    return modalBody;
}

function applyEventListenerToCheckoutBtn() {
    const checkoutBtn = document.querySelector("#checkout-btn");
    checkoutBtn.addEventListener("click", () => {
        const modal = createCheckoutForm("creditCard", "Credit Card Information");
        const layer = document.createElement("div");
        layer.classList.add("layer");
        document.body.appendChild(modal);
        document.body.appendChild(layer);
        const cancelBtn = document.querySelector(".cancel-btn");
        cancelBtn.addEventListener("click", () => {
            modal.remove();
            layer.remove();
        })
    })
}

applyEventListenerToCheckoutBtn();