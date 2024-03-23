const nodeMailer = require('nodemailer')

const transporter = nodeMailer.createTransport({
    service:"gmail",
    auth:{
        user:"truongnqpd07800@fpt.edu.vn",
        pass:"lmyw ksvz lvip mnxw"
    }
})
module.exports = transporter
