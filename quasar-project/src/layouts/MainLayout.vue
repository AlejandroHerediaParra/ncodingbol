<template>
  <q-layout view="hHh lpr fFf">
    <q-header bordered class="bg-white text-black">
      <q-toolbar>
        <q-avatar size="40px">
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo.svg" />
        </q-avatar>
        <q-space></q-space>
        <q-btn-group unelevated>
          <q-btn
            v-for="toolbarLink in toolbarLinks"
            :label="toolbarLink.title"
            :to="toolbarLink.link"
            :key="toolbarLink.title"
            icon-right="keyboard_arrow_down"
            v-bind="toolbarLink"
            type="a"
          />
        </q-btn-group>
        <q-space></q-space>
        <q-btn color="red-8" label="Register or Lorgin" @click="userModal = true"/>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view :key="$route.path"/>
    </q-page-container>

    <q-footer bordered class="bg-grey-8 text-white">
      <q-toolbar>
        <q-space></q-space>
        test link
        <q-space></q-space>
      </q-toolbar>
    </q-footer>
    <!-- <q-dialog v-model="userModal">
      <q-card>
        <q-card-section>
          <div class="text-h6">Alert</div>
        </q-card-section>
        <q-card-section>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. porro. Rerum blanditiis perferendis totam, ea at omnis vel numquam exercitationem aut, natus minima, porro labore.
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog> -->
  </q-layout>
</template>

<script>
import { defineComponent, ref } from 'vue'

const linksList = [
  {
    title: 'Available Courses',
    link: '/'
  },
  {
    title: 'Continuing Education',
    link: '/'
  },
  {
    title: 'My COurses',
    link: '/user'
  },
  {
    title: 'Community',
    link: ''
  }
]

export default defineComponent({
  name: 'MainLayout',

  components: {
    // EssentialLink
  },

  setup () {
    const leftDrawerOpen = ref(false)

    return {
      userModal: ref(false),
      toolbarLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
})
</script>
