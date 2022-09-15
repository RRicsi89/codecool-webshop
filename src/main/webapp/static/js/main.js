async function apiGet(url) {
    let response = await fetch(url, {method: "GET"});
    if(response.status === 200) {
        return response.json();
    }
}
