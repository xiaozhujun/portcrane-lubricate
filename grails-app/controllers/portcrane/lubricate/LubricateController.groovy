package portcrane.lubricate

import db.insertToDb
import org.springframework.dao.DataIntegrityViolationException
class LubricateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [lubricateInstanceList: Lubricate.list(params), lubricateInstanceTotal: Lubricate.count()]
    }

    def create() {
        [lubricateInstance: new Lubricate(params)]
    }

    def save() {
        def lubricateInstance = new Lubricate(params)
        if (!lubricateInstance.save(flush: true)) {
            render(view: "create", model: [lubricateInstance: lubricateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), lubricateInstance.id])
        insertToDb.updateF(lubricateInstance.id)
        redirect(action: "show", id: lubricateInstance.id)
    }

    def show(Long id) {
        def lubricateInstance = Lubricate.get(id)
        if (!lubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "list")
            return
        }

        [lubricateInstance: lubricateInstance]
    }

    def edit(Long id) {
        def lubricateInstance = Lubricate.get(id)
        if (!lubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "list")
            return
        }

        [lubricateInstance: lubricateInstance]
    }

    def update(Long id, Long version) {
        def lubricateInstance = Lubricate.get(id)
        if (!lubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lubricateInstance.version > version) {
                lubricateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'lubricate.label', default: 'Lubricate')] as Object[],
                        "Another user has updated this Lubricate while you were editing")
                render(view: "edit", model: [lubricateInstance: lubricateInstance])
                return
            }
        }

        lubricateInstance.properties = params

        if (!lubricateInstance.save(flush: true)) {
            render(view: "edit", model: [lubricateInstance: lubricateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), lubricateInstance.id])
        redirect(action: "show", id: lubricateInstance.id)
    }

    def delete(Long id) {
        def lubricateInstance = Lubricate.get(id)
        if (!lubricateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "list")
            return
        }

        try {
            lubricateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lubricate.label', default: 'Lubricate'), id])
            redirect(action: "show", id: id)
        }
    }
    def archive(){
        render (view:"archive")
    }
}
