<template>
  <div class="card-padding">
    <v-card width="300">
      <v-card-title
        ><span class="mx-auto">{{ skill.name }}</span></v-card-title
      >
      <div class="card-text-fix">
        <v-card-text class="align-end fill-height">
          <strong>
            {{ skill.description }}
          </strong>
        </v-card-text>
      </div>
      <div class="text-center">
        <router-link
          :to="'/edit-skill-form/' + skill.id"
          tag="span"
          class="pointerClass"
        >
          <v-btn color="danger" class="mr-2">
            <span>EDIT</span>
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </router-link>
        <v-btn color="danger" class="ml-2">
          <span>DELETE</span>
          <v-icon>mdi-trash-can-outline</v-icon>
        </v-btn>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    skill: {
      type: Object,
      require: true
    }
  }
};
</script>
