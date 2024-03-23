const multer = require('multer');

const _storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/uploads/');
    },
    filename: function (req, file, cb) {
        cb(null, `${Date.now()}-${file.originalname}`); // Sửa thành Date.now() thay vì Data.now()
    }
});

const upload = multer({
    storage: _storage, // Sửa thành storage thay vì storge
    limits: {
        fileSize: 5 * 1024 * 1024 // Sửa thành fileSize thay vì fieldSize
    }
});
module.exports =  upload