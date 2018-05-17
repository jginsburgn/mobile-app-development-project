function promotion() {
  return {
    schema: {
      name: {
        type: 'string'
      },
      product: {
        type: 'string'
      },
      initialDate: {
        type: 'string'
      },
      finalDate: {
        type: 'string'
      },
      type: {
        type: 'string'
      }
    }
  };
}

module.exports = promotion;
