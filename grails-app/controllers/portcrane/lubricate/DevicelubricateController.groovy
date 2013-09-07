package portcrane.lubricate

import org.springframework.dao.DataIntegrityViolationException

class DevicelubricateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [devicelubricateInstanceList: Devicelubricate.list(params), devicelubricateInstanceTotal: Devicelubricate.count()]
    }

    def create() {
        [devicelubricateInstance: new Devicelubricate(params)]
    }

    def save() {
        def devicelubricateInstance = new Devicelubricate(params)
        if (!devicelubricateInstance.save(flush: true)) {
            render(view: "create", model: [devicelubricateInstance: devicelubricateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), devicelubricateInstance.id])
        redirect(action: "show", id: devicelubricateInstance.id)
    }

    def show(Long id) {
        def devicelubricateInstance = Devicelubricate.get(id)
        if (!devicelubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "list")
            return
        }

        [devicelubricateInstance: devicelubricateInstance]
    }

    def edit(Long id) {
        def devicelubricateInstance = Devicelubricate.get(id)
        if (!devicelubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "list")
            return
        }

        [devicelubricateInstance: devicelubricateInstance]
    }

    def update(Long id, Long version) {
        def devicelubricateInstance = Devicelubricate.get(id)
        if (!devicelubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (devicelubricateInstance.version > version) {
                devicelubricateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'devicelubricate.label', default: 'Devicelubricate')] as Object[],
                          "Another user has updated this Devicelubricate while you were editing")
                render(view: "edit", model: [devicelubricateInstance: devicelubricateInstance])
                return
            }
        }

        devicelubricateInstance.properties = params

        if (!devicelubricateInstance.save(flush: true)) {
            render(view: "edit", model: [devicelubricateInstance: devicelubricateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), devicelubricateInstance.id])
        redirect(action: "show", id: devicelubricateInstance.id)
    }

    def delete(Long id) {
        def devicelubricateInstance = Devicelubricate.get(id)
        if (!devicelubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "list")
            return
        }

        try {
            devicelubricateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'devicelubricate.label', default: 'Devicelubricate'), id])
            redirect(action: "show", id: id)
        }
    }
}
