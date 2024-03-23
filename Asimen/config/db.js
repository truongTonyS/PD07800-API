const mongoose = require('mongoose')
mongoose.set('strictQuery', true)

const local = "mongodb://localhost:27017/MyDatabase"

const atlat = "mongodb+srv://giang_26:giang123@cluster0.hk8pneu.mongodb.net/MyDatabase?retryWrites=true&w=majority&appName=Cluster0"

const connect = async () => {
    try {
        await mongoose.connect(local,
            {
                useNewUrlParser:true,
                useUnifiedTopology: true,
            })
            console.log('connect success')
    } catch (error) {
        console.log(error)
        console.log('connect fail')
    }
}

module.exports = {connect}