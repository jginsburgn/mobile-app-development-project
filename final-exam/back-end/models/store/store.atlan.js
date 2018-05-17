function store() {
  return {
    schema: {
      name: {
        type: 'string'
      },
      location: {
        type: 'string'
      },
      description: {
        type: 'string'
      },
      image: {
        type: 'string'
      },
      owner: {
        type: 'string'
      },
      phone: {
        type: 'string'
      },
      products: {
        type: 'array',
        elements: {
          type: 'string'
        }
      }
    },
    hooks: {
      post: {
        before(req, res, next) {
          req.body.image =
            'http://ludo.tenatek.com/' +
            (Math.floor(Math.random() * 7) + 1) +
            '.jpg';
          next();
        }
      }
    }
  };
}

module.exports = store;
