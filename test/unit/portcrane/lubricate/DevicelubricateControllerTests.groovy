package portcrane.lubricate



import org.junit.*
import grails.test.mixin.*

@TestFor(DevicelubricateController)
@Mock(Devicelubricate)
class DevicelubricateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/devicelubricate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.devicelubricateInstanceList.size() == 0
        assert model.devicelubricateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.devicelubricateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.devicelubricateInstance != null
        assert view == '/devicelubricate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/devicelubricate/show/1'
        assert controller.flash.message != null
        assert Devicelubricate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/devicelubricate/list'

        populateValidParams(params)
        def devicelubricate = new Devicelubricate(params)

        assert devicelubricate.save() != null

        params.id = devicelubricate.id

        def model = controller.show()

        assert model.devicelubricateInstance == devicelubricate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/devicelubricate/list'

        populateValidParams(params)
        def devicelubricate = new Devicelubricate(params)

        assert devicelubricate.save() != null

        params.id = devicelubricate.id

        def model = controller.edit()

        assert model.devicelubricateInstance == devicelubricate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/devicelubricate/list'

        response.reset()

        populateValidParams(params)
        def devicelubricate = new Devicelubricate(params)

        assert devicelubricate.save() != null

        // test invalid parameters in update
        params.id = devicelubricate.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/devicelubricate/edit"
        assert model.devicelubricateInstance != null

        devicelubricate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/devicelubricate/show/$devicelubricate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        devicelubricate.clearErrors()

        populateValidParams(params)
        params.id = devicelubricate.id
        params.version = -1
        controller.update()

        assert view == "/devicelubricate/edit"
        assert model.devicelubricateInstance != null
        assert model.devicelubricateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/devicelubricate/list'

        response.reset()

        populateValidParams(params)
        def devicelubricate = new Devicelubricate(params)

        assert devicelubricate.save() != null
        assert Devicelubricate.count() == 1

        params.id = devicelubricate.id

        controller.delete()

        assert Devicelubricate.count() == 0
        assert Devicelubricate.get(devicelubricate.id) == null
        assert response.redirectedUrl == '/devicelubricate/list'
    }
}
