package portcrane.lubricate



import org.junit.*
import grails.test.mixin.*

@TestFor(LubricateController)
@Mock(Lubricate)
class LubricateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lubricate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lubricateInstanceList.size() == 0
        assert model.lubricateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.lubricateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.lubricateInstance != null
        assert view == '/lubricate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lubricate/show/1'
        assert controller.flash.message != null
        assert Lubricate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lubricate/list'

        populateValidParams(params)
        def lubricate = new Lubricate(params)

        assert lubricate.save() != null

        params.id = lubricate.id

        def model = controller.show()

        assert model.lubricateInstance == lubricate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lubricate/list'

        populateValidParams(params)
        def lubricate = new Lubricate(params)

        assert lubricate.save() != null

        params.id = lubricate.id

        def model = controller.edit()

        assert model.lubricateInstance == lubricate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lubricate/list'

        response.reset()

        populateValidParams(params)
        def lubricate = new Lubricate(params)

        assert lubricate.save() != null

        // test invalid parameters in update
        params.id = lubricate.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lubricate/edit"
        assert model.lubricateInstance != null

        lubricate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lubricate/show/$lubricate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lubricate.clearErrors()

        populateValidParams(params)
        params.id = lubricate.id
        params.version = -1
        controller.update()

        assert view == "/lubricate/edit"
        assert model.lubricateInstance != null
        assert model.lubricateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lubricate/list'

        response.reset()

        populateValidParams(params)
        def lubricate = new Lubricate(params)

        assert lubricate.save() != null
        assert Lubricate.count() == 1

        params.id = lubricate.id

        controller.delete()

        assert Lubricate.count() == 0
        assert Lubricate.get(lubricate.id) == null
        assert response.redirectedUrl == '/lubricate/list'
    }
}
