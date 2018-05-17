// DEVELOPMENT ONLY

const dotenv = require('dotenv');

dotenv.load();

// INITIALIZE APP

const express = require('express');

const app = express();

// SET HEADERS

const { setHeaders } = require('./services/headers');

setHeaders(app);

// CRUD API

const Atlan = require('atlan');

const atlan = new Atlan();
app.use('/api', atlan.router());

// CONNECT TO DB

const { initializeDatabase } = require('./services/database');

initializeDatabase(atlan, app);

// START SERVER

app.use(express.static('images'));

app.listen(process.env.PORT);
