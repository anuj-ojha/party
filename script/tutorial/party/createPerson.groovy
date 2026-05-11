import org.moqui.context.ExecutionContext
ExecutionContext ec = context.ec

// 1. Check if Party exists
def party = ec.entity.find("tutorial.party.Party").condition("partyId", partyId).one()

if (party == null) {
    ec.message.addError("Party with ID ${partyId} does not exist. Cannot create Person.")
    return
}

// 2. Create the Person record
// Using ec.service.sync to call the implicit entity-auto service
ec.service.sync().name("create#tutorial.party.Person").parameters(context).call()

// 3. Set the success message
response = "Person ${firstName} ${lastName} created successfully!"
