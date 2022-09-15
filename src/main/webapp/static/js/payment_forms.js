function createCreditCardForm(paymentType, headerMsg) {

    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal");

    const modalHeader = document.createElement("div");
    modalHeader.innerText = headerMsg;
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
        "<form id='payment-form' action='/api/process-payment' method='POST'>" +
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
        "<form id='payment-form' action='/api/process-payment' method='POST'>" +
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
        const modal = createCreditCardForm("creditCard", "Credit Card Information");
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
        const modal = createCreditCardForm("paypal", "PayPal Account Information");
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

function createConfirmationWindow(msg) {
    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal");

    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");

    modalBody.innerText = msg;
    modalBody.style.fontSize = "30px";

    const modalFooter = document.createElement("div");
    modalFooter.classList.add("modal-footer");
    const confirmBtn = document.createElement("button");
    confirmBtn.classList.add("confirm-btn", "popup-btn");
    confirmBtn.innerText = "Yes";
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancel-confirm-btn", "popup-btn");
    cancelBtn.innerText = "Cancel";
    modalFooter.appendChild(confirmBtn);
    modalFooter.appendChild(cancelBtn);

    modalContainer.appendChild(modalBody);
    modalContainer.appendChild(modalFooter);

    return modalContainer;
}

function applyEventListenerToCancelOrderBtn() {
    const cancelOrderBtn = document.querySelector("#cancel-order-btn");
    cancelOrderBtn.addEventListener("click", () => {
        const popup = createConfirmationWindow("Are you sure to delete order?");
        const layer = document.createElement("div");
        layer.classList.add("layer");
        document.body.appendChild(layer);
        document.body.appendChild(popup);

        const confirmBtn = document.querySelector(".confirm-btn");
        const cancelBtn = document.querySelector(".cancel-confirm-btn");

        cancelBtn.addEventListener("click", () => {
            popup.remove();
            layer.remove();
        })

        confirmBtn.addEventListener("click", async function() {
            const orderId = 1;
            let response = await fetchDelete(orderId);
            if (response.status === 200) {
                popup.remove();
                layer.remove();
                window.location = "/";
            } else {
                alert("Deletion of Order unsuccessful");
                popup.remove();
                layer.remove();
            }
        })

    })
}

async function fetchDelete(orderId) {
    let response = await fetch(`api/order/delete?orderId=${orderId}`,
        {method: "DELETE"});
    return response;
}

function main() {
    applyEventListenerToCreditBtn();
    applyEventListenerToPayPalBtn();
    applyEventListenerToCancelOrderBtn();
}

main();
