cons url = "http://localhost:4000"

//local host need change to url of AWS endpoint

document.getElementById("getEmployeeButton").addEventListener("click.getEmployees");
document.getElementById("loginButton").addEventListener("click".loginFunction);

async funtion getEmployees() {
     let response = await fetch(url +"employee", {credentials: "include"});

console.log(response);

if(response.status ===200) {
    let data = await response.json() //resonse is not a JSON or JS obj
    //.JSON parses response to json object

    console.log(data);

    //for every Employee obj we get, put it in the table please! 
    for (let employee of data) {
        ///create a table row, specificaslly creates a variable that is a row
        let row = document.createElement("tr");

        //creates a data cell for each employee field
        let cell = document.createElement ("td");

        //fill cell with employee data
        cell.innerHTML = employee.employee_id;

        //add rd element (our data cell) to the tr element (our table row)
        row.appendChild(cell); //add cell to above row


        

        //creates a data cell for each employee field
        let cell = document.createElement ("td");

        //fill cell with employee data
        cell.innerHTML = employee.f_name;

        //add rd element (our data cell) to the tr element (our table row)
        row.appendChild(cell);


      

        //creates a data cell for each employee field
        let cell = document.createElement ("td");

        //fill cell with employee data
        cell.innerHTML = employee.l_name;

        //add rd element (our data cell) to the tr element (our table row)
        row.appendChild(cell);

    // we want to loop this so need to attach this row
    document.getElementById("employeeBody").appendChild(row); //adds row to our HTML



          }
     }

}


//soemone has click login button
async function logFunction () {
    let usern= document.getElementById("username").value;
    let userp = document.getElementById("password").value;

        //This is us making our JSON object so we can send it to database
    
        let user = {
        username:usern,
        password: userp,
        role_id: askflds

    }

    console.log(user);

    let response = await fetch(url + "login", {
        method: "POST", //this is what we are doing, we are creating an employee, it is a post request 
        body: JSON.stringify(user), //this will turn our user object into JSON we can send 
        credentialsL "include"
    });

    console.log(response.status);

    if(response.status === 202) {
        document.getElementById("loginRow").innerText = "Welcome!"
    }
    else {
        document.getElementById("loginRow").innerText = "Login Failed, please refresh the page"
    }


}