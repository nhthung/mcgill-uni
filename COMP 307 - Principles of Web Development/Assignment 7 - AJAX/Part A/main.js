/* Author: Le Nhat Hung, 260793376
 */

/* Handler for 'submit' event. Main function */
const handleSubmit = event => {

    /* Prevent form from submitting */
    event.preventDefault();

    /* Generate JSON from form data */
    const data = formToJSON(form.elements);

    /* Write preview of generated JSON */
    const dataContainer = document.getElementById('code-preview');
    dataContainer.textContent = JSON.stringify(data, null, " ");

    /* Send JSON to Python script */
    postData(data);
}

/* AJAX request sending JSON data into Python script's stdin */
const postData = data => {
    $.ajax({
        type: "POST",
        url: "main.py",
        data: JSON.stringify(data),
        dataType: "application/json"
    });
}

/* Convert form data into JSON form */
const formToJSON = elements => [].reduce.call(elements, (data, element) => {

    if (isValidElement(element)) {
        data[element.name] = element.value;
    }

    return data;
}, {});

/* The button is considered an element. But its value is "", which is invalid
 * and has to be excluded */
isValidElement = element => {
    return element.value;
}


/* Listener for 'submit' event */
const form = document.getElementById("form");
form.addEventListener('submit', handleSubmit);
