<template>
  <q-card flat class="my-card col-3 q-ma-md">
    <q-img rounded src="https://cdn.quasar.dev/img/mountains.jpg">
        <div class=" absolute-bottom">
            <q-badge rounded color="grey" :label="tag" class="text-black" />
        </div>
    </q-img>
    <q-card-section class="q-px-none q-py-sm">
        <div class="text-subtitle2">{{name}}</div>
    </q-card-section>
    <q-card-section class="q-px-none q-py-sm">
        {{description}}
    </q-card-section>
    <q-card-section class="q-px-none q-py-none">
        <div class="text-black flex" style="font-size: 1em">
            <q-icon name="star"/>{{rate}} <div class="text-grey-8">({{votes}})</div>
        </div>
    </q-card-section>
    <q-card-section class="q-px-none q-py-sm">
        <q-icon name="schedule"/>{{duration}}
    </q-card-section>
  </q-card>
</template>
<script>
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'CourseCard',
  props: {
    id: {
      type: Number,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    description: {
      type: String,
      required: true
    },
    duration: {
      type: String,
      required: true
    },
    rate: {
      type: Number,
      required: true
    },
    tag: {
      type: String,
      required: true
    },
    votes: {
      type: Number,
      required: true
    }
  }
})
</script>
