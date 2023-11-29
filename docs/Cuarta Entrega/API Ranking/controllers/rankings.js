const { validateEntities } = require('../schemas/rankings.js')
const { EntityModel } = require('../models/rankings.js')

class EntityController {
  static calculateRanking (req, res) {
    const result = validateEntities(req.body)

    if (!result.success) {
      // 422 Unprocessable Entity
      return res.status(400).json({ error: JSON.parse(result.error.message) })
    }

    const orderedEntities = EntityModel.getOrderedEntities(result.data)

    res.status(200).json(orderedEntities)
  }
}

module.exports = { EntityController }
