const CustomError = require('../helpers/CustomError');
const Validate = require('../helpers/Validate');

const validateMiddleware = (req, res, next) => {

    const endpoint = req.originalUrl.split("/api/")[1];

    const inValid = Validate.check(endpoint, req.body);

    if(inValid.length>0){
        return next(new CustomError(`Please check your input`,400, inValid))
    }

    next();
}

module.exports = validateMiddleware;