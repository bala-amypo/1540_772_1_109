@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                jwtUtil.extractEmail(token),
                                null,
                                List.of(new SimpleGrantedAuthority(
                                        "ROLE_" + jwtUtil.extractRole(token)
                                ))
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
