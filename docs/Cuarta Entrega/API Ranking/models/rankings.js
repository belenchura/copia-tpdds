class EntityModel {
  static getOrderedEntities (input) {
    const response = input.entities.map((entity) => {
      return {
        name: entity.name,
        levelOfImpact: calcularNivelDeImpacto(entity)
      }
    }
    ).sort((a, b) => {
      return b.levelOfImpact - a.levelOfImpact
    })
    return response
  }
}

module.exports = { EntityModel }

function calcularNivelDeImpacto (entity) {
  let sumResTime = 0
  let cantIncidentesNoResueltos = 0
  const CNF = 3
  for (const incident of entity.incidents) {
    if (incident.sumResTime >= 0) {
      sumResTime += incident.sumResTime
    } else {
      cantIncidentesNoResueltos++
    }
  }
  return (sumResTime + cantIncidentesNoResueltos * CNF) * entity.ammountOfAffectedMembers
}
