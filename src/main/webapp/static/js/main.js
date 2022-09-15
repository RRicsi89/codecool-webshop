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
