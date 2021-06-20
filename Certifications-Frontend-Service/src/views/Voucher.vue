<template>
  <div class="home">
    <v-row>
      <v-col cols="12">
        <h1>Available Vouchers</h1>
        <div v-if="vouchers !== undefined && vouchers.length > 0">
          <v-row>
            <router-link
              v-for="voucher in vouchers"
              :key="voucher.id"
              :to="'/voucherDetail/' + voucher.id"
              tag="div"
            >
              <v-col>
                <VoucherItem style="cursor: pointer" :voucher="voucher" />
              </v-col>
            </router-link>
          </v-row>
        </div>
        <div v-else>
          <h3>No Vouchers found</h3>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import VoucherItem from "@/components/VoucherItem.vue";
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default Vue.extend({
  name: "Voucher",
  components: {
    VoucherItem,
  },
  computed: {
    ...mapGetters(["vouchers"]),
  },
  async mounted() {
    if (this.loggedIn == false) {
      this.$router.push("/login");
    }
    const { data } = await axios.get("http://localhost:8080/vouchers");
    console.log(data._embedded.vouchers)
    this.vouchersMutation(data._embedded.vouchers);
  },
  methods: {
    ...mapMutations(["vouchersMutation"]),
  },
});
</script>

<style></style>
