const form = document.querySelector("form");
const fullname = document.getElementById("Name");
const email = document.getElementById("email");
const number = document.getElementById("Number");
const subject = document.getElementById("Subject");
const message = document.getElementById("Message");

function sendEmail(){
    const bodymessage = `<b> FULLNAME: </b> ${fullname.value}<br>
    <b> Email: </b> ${email.value}<br> 
    <b> Phone No: </b> ${number.value}<br> 
    <b> Message: </b> ${message.value} `;
    Email.send({
        Host : "smtp.elasticemail.com",
        Username : "talarijanardhan7330@gmail.com",
        Password : "AED069A4FA254D9188B59B4ED917CDA5CB47",
        To : 'talarijanardhan7330@gmail.com',
        From : "talarijanardhan7330@gmail.com",
        Subject : subject.value,
        Body : bodymessage
    }).then(
      message => {
        if(message == "OK"){
            Swal.fire({
                title: "sent!",
                text: "Message sent successfully!",
                icon: "success"
              });
        }
      }
    );
}

form.addEventListener("submit", (e) =>{
    e.preventDefault();

    sendEmail();
} );