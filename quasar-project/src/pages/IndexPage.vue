<template>
  <q-page class="q-pa-xl q-ma-auto row items-start q-gutter-lg flex-center">
    <CourseCard
      v-for="course in courses"
      :hey="course.id"
      v-bind:key="course"
      :name="course.name"
      :description="course.description"
      :id="course.id"
      :duration="course.duration"
      :rate="course.rate"
      :tag="course.tag"
      :votes="course.votes"
    />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import CourseCard from 'src/components/CourseCard.vue'
import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default defineComponent({
  data () {
    return {
      courses: []
    }
  },
  name: 'IndexPage',
  components: { CourseCard },
  computed: {
    ...mapState(['user/user'])
  },
  methods: {

    ...mapActions(['user/fetchUser']),
    fetchCourses () {
      this.courses = []
      if (this.$route.path !== '/user') {
        axios.get('http://localhost:9090/api/course').then((response) => {
          this.courses = response.data
        })
      } else {
        this.$store.dispatch('user/fetchUser').then(() => {
          this.courses = this.$store.state.user.user.courses
        })
      }
    }
  },
  mounted () {
    this.fetchCourses()
  }
})
</script>
