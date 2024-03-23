// users.js - model file
const mongoose = require('mongoose');
const Scheme = mongoose.Schema;

const UsersSchema = new Scheme ({
    username: { type: String, unique: true, maxlength: 255 },
    password: { type: String },
    email: { type: String, unique: true },
    name: { type: String },
    avatar: { type: String },
    age: { type: Number, min: 10, max: 65 },
    available: { type: Boolean, default: false },
}, {
    timestamps: true // Corrected typo here
});

module.exports = mongoose.model('User', UsersSchema);
