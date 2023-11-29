const { Router } = require('express')
const { EntityController } = require('../controllers/rankings.js')

const rankingsRouter = Router()

rankingsRouter.get('/', EntityController.calculateRanking)

module.exports = { rankingsRouter }
