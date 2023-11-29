const z = require('zod')

const entitySchema =
z.object({
  entities: z.array(
    z.object({
      name: z.string(),
      ammountOfAffectedMembers: z.number().int(),
      incidents: z.array(
        z.object({
          resTime: z.number().int()
        })
      )
    }))
})

const validateEntities = (input) => {
  return entitySchema.safeParse(input)
}

module.exports = {
  validateEntities
}
