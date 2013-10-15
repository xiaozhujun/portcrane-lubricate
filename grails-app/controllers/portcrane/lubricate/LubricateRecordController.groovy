package portcrane.lubricate

import dbmodel.lubricate
import org.springframework.dao.DataIntegrityViolationException
import db.insertToDb
class LubricateRecordController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [lubricateRecordInstanceList: LubricateRecord.list(params), lubricateRecordInstanceTotal: LubricateRecord.count()]
    }

    def create() {
        [lubricateRecordInstance: new LubricateRecord(params)]
    }

    def save() {
        def lubricateRecordInstance = new LubricateRecord(params)
        if (!lubricateRecordInstance.save(flush: true)) {
            render(view: "create", model: [lubricateRecordInstance: lubricateRecordInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), lubricateRecordInstance.id])
        redirect(action: "show", id: lubricateRecordInstance.id)
    }

    def show(Long id) {
        def lubricateRecordInstance = LubricateRecord.get(id)
        if (!lubricateRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "list")
            return
        }

        [lubricateRecordInstance: lubricateRecordInstance]
    }

    def edit(Long id) {
        def lubricateRecordInstance = LubricateRecord.get(id)
        System.out.print("--------------")
        if (!lubricateRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "list")
            return
        }

        [lubricateRecordInstance: lubricateRecordInstance]
    }

    def update(Long id, Long version) {
        def lubricateRecordInstance = LubricateRecord.get(id)
        System.out.print("hahahhaha")
        if (!lubricateRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lubricateRecordInstance.version > version) {
                lubricateRecordInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lubricateRecord.label', default: 'LubricateRecord')] as Object[],
                          "Another user has updated this LubricateRecord while you were editing")
                render(view: "edit", model: [lubricateRecordInstance: lubricateRecordInstance])
                return
            }
        }

        lubricateRecordInstance.properties = params

        if (!lubricateRecordInstance.save(flush: true)) {
            render(view: "edit", model: [lubricateRecordInstance: lubricateRecordInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), lubricateRecordInstance.id])
        String devid=insertToDb.getdevid(lubricateRecordInstance.id)
        List<Integer> list=insertToDb.getItemId(devid)
        Iterator it=list.iterator()
        while(it.hasNext()){
            int l1=(int)it.next()
            insertToDb.updateF1(l1)
        }
        redirect(action: "show", id: lubricateRecordInstance.id)
    }

    def delete(Long id) {
        def lubricateRecordInstance = LubricateRecord.get(id)
        if (!lubricateRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "list")
            return
        }

        try {
            lubricateRecordInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lubricateRecord.label', default: 'LubricateRecord'), id])
            redirect(action: "show", id: id)
        }
    }
}
