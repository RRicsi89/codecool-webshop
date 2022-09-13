function createCreditCardForm(paymentType) {

    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal");

    const modalHeader = document.createElement("div");
    modalHeader.innerText = "Credit Card Information";
    modalHeader.classList.add("modal-header");

    const modalFooter = document.createElement("div");
    modalFooter.classList.add("modal-footer");
    const submitBtn = document.createElement("button");
    submitBtn.classList.add("submit-btn");
    submitBtn.innerText = "Accept";
    submitBtn.type = "submit";
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancel-btn");
    cancelBtn.innerText = "Cancel";
    modalFooter.appendChild(submitBtn);
    modalFooter.appendChild(cancelBtn);

    let modalBody;
    if (paymentType === "creditCard") {
        modalBody = createModalBodyForCredit();
    } else if (paymentType === "paypal") {
        modalBody = createModalBodyForPayPal();
    }

    submitBtn.setAttribute("form", "payment-form");

    modalContainer.appendChild(modalHeader);
    modalContainer.appendChild(modalBody);
    modalContainer.appendChild(modalFooter);

    return modalContainer;
}

function createModalBodyForCredit() {
    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalBody.innerHTML =
        "<form id='payment-form' action='/process-payment' method='POST'>" +
            "<label for='card-number'>Card number</label></br>" +
            "<input type='number' name='card-number' id='card-number' required></br></br>" +
            "<label for='card-holder'>Card holder</label></br>" +
            "<input type='text' name='card-holder' id='card-holder' required></br></br>" +
            "<label for='exp-date'>Expiration date</label></br>" +
            "<input type='text' name='expiration' id='exp-date' required></br></br>" +
            "<label for='cvv-code'>CCV coder</label></br>" +
            "<input type='number' name='cvv' id='cvv-code' required></br></br>" +
        "</form>"
    return modalBody;
}

function createModalBodyForPayPal() {
    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalBody.innerHTML =
        "<form id='payment-form' action='/process-payment' method='POST'>" +
            "<label for='username'>Username</label></br>" +
            "<input type='text' name='username' id='username' required></br></br>" +
            "<label for='password'>Password</label></br>" +
            "<input type='password' name='password' id='password' required></br></br>" +
        "</form>"
    return modalBody;
}

function applyEventListenerToCreditBtn() {
    const creditBtn = document.querySelector("#credit-btn");
    creditBtn.addEventListener("click", () => {
        const modal = createCreditCardForm("creditCard");
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

function applyEventListenerToPayPalBtn() {
    const paypalBtn = document.querySelector("#paypal-btn");
    paypalBtn.addEventListener("click", () => {
        const modal = createCreditCardForm("paypal");
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

function main() {
    applyEventListenerToCreditBtn();
    applyEventListenerToPayPalBtn();
}

main();
