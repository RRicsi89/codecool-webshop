
function createLoginModal(message) {
    const modal = document.createElement("div");
    const modalBody = createLoginModalBody();
    const modalHeader = createLoginModalHeader();
    const modalFooter = createLoginModalFooter(message);

    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal");

    modalContainer.appendChild(modalHeader);
    modalContainer.appendChild(modalBody);
    modalContainer.appendChild(modalFooter);

    return modalContainer;
}

function createLoginModalFooter(message) {
    const modalFooter = document.createElement("div");
    modalFooter.classList.add("modal-footer");
    const submitBtn = document.createElement("button");
    submitBtn.classList.add("submit-btn");
    submitBtn.innerText = message;
    submitBtn.type = "submit";
    const cancelBtn = document.createElement("button");
    cancelBtn.classList.add("cancel-btn");
    cancelBtn.innerText = "Cancel";
    modalFooter.appendChild(submitBtn);
    modalFooter.appendChild(cancelBtn);
    submitBtn.setAttribute("form", "login-form");
    return modalFooter;
}

function createLoginModalHeader() {
    const modalHeader = document.createElement("div");
    modalHeader.innerText = "User information";
    modalHeader.classList.add("modal-header");
    return modalHeader;
}

function createLoginModalBody() {
    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalBody.innerHTML =
        "<form id='login-form' action='/login' method='POST'>" +
        "<label for='username'>Name</label></br>" +
        "<input type='text' name='name' id='username' required></br></br>" +
        "<label for='password'>Password</label></br>" +
        "<input type='password' name='password' id='password' required></br></br>" +
        "</form>"
    return modalBody;
}

function applyEventListenerToLoginBtn() {
    const loginBtn = document.querySelector("#login-btn");
    loginBtn.addEventListener("click", () => {
        const modal = createLoginModal("Login");
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

applyEventListenerToLoginBtn();