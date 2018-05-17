const client = require('./client/client.atlan');
const promotion = require('./promotion/promotion.atlan');
const store = require('./store/store.atlan');

function models(atlan, app, db) {
  atlan.model([
    ['client', client(db)],
    ['promotion', promotion(db)],
    ['store', store(db)]
  ]);
}

module.exports = { models };
