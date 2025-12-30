const API_URL = "http://localhost:8085/api/departments";

window.onload = loadDepartments;

// LOAD ALL
function loadDepartments() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            let table = document.getElementById("departmentTable");
            table.innerHTML = "";
            data.forEach(d => {
                table.innerHTML += `
                    <tr>
                        <td>${d.id}</td>
                        <td>${d.name}</td>
                        <td>${d.location}</td>
                        <td>
                            <button onclick="editDepartment(${d.id}, '${d.name}', '${d.location}')">
                                Edit
                            </button>
                            <button class="delete-btn" onclick="deleteDepartment(${d.id})">
                                Delete
                            </button>
                        </td>
                    </tr>
                `;
            });
        });
}

// ADD
function addDepartment() {
    let name = document.getElementById("name").value;
    let location = document.getElementById("location").value;

    if (name === "" || location === "") {
        alert("Fill all fields");
        return;
    }

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, location })
    })
    .then(() => {
        clearForm();
        loadDepartments();
    });
}

// LOAD DATA INTO FORM (EDIT)
function editDepartment(id, name, location) {
    document.getElementById("deptId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("location").value = location;

    document.getElementById("addBtn").style.display = "none";
    document.getElementById("updateBtn").style.display = "block";
}

// UPDATE
function updateDepartment() {
    let id = document.getElementById("deptId").value;
    let name = document.getElementById("name").value;
    let location = document.getElementById("location").value;

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, location })
    })
    .then(() => {
        clearForm();
        loadDepartments();
    });
}

// DELETE
function deleteDepartment(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(() => loadDepartments());
}

// RESET FORM
function clearForm() {
    document.getElementById("deptId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("location").value = "";

    document.getElementById("addBtn").style.display = "block";
    document.getElementById("updateBtn").style.display = "none";
}
