const nodemailer = require('nodemailer');

const mailHelper = async(mailOptions) => {
    let transporter = nodemailer.createTransport({
        host : process.env.SMTP_HOST,
        port : process.env.SMTP_PORT,
        auth: {
            user : process.env.SMTP_USER,
            pass : process.env.SMTP_PASS
        }
    });

    let info = await transporter.sendMail(mailOptions);
    console.log(`Message Send : ${info.messageId}`);
}

module.exports = mailHelper;