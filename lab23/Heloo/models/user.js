const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Users = new Schema({
    username : {type: String},
    password: {type: String},
    // email: {type: String, unique: true},
    email: {type: String},
    name: {type: String},
    avatar: {type: String},
    age: { type: Number, min: 18, max: 85 },
    available: {type: Boolean, default: true}
},{
    timestamps: true
})

module.exports = mongoose.model('user', Users)