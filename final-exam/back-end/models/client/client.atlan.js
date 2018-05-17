function client() {
  return {
    schema: {
      name: {
        type: 'string'
      },
      paternalName: {
        type: 'string'
      },
      maternalName: {
        type: 'string'
      },
      address: {
        type: 'string'
      },
      phone: {
        type: 'string'
      },
      email: {
        type: 'string'
      },
      password: {
        type: 'string'
      },
      purchases: {
        type: 'array',
        elements: {
          type: 'ref',
          model: 'promotion'
        }
      }
    }
  };
}

module.exports = client;
