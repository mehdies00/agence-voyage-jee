async function fetchHeader() {
    try {
        const reponse = await fetch("./header.html");
        if (!reponse.ok) {
            throw new Error("echec du fetchHeader");
        }

        const header = await reponse.text(); // html et pas header

        const headerContainer = document.querySelector("header");
        headerContainer.innerHTML = header;

    } catch (e) {
        console.error(e);
    }
}
fetchHeader();