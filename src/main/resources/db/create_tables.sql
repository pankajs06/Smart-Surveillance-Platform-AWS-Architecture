-- Optional: SQL schema for PostgreSQL
CREATE TABLE IF NOT EXISTS users (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  username text,
  name text,
  email text,
  roles text,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS cameras (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  camera_id text,
  site_id text,
  label text,
  rtsp_url text,
  status text,
  last_seen timestamptz
);

CREATE TABLE IF NOT EXISTS agents (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  agent_id text,
  site_id text,
  ip text,
  capabilities text,
  registered_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS recordings (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  camera_id text,
  start_at timestamptz,
  end_at timestamptz,
  size_bytes bigint,
  storage_url text
);

CREATE TABLE IF NOT EXISTS transcode_jobs (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  camera_id text,
  source text,
  status text,
  progress integer,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS stream_allocations (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  camera_id text,
  agent_id text,
  proxy_endpoint text,
  expires_at timestamptz
);

CREATE TABLE IF NOT EXISTS compare_sessions (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  left_recording_id text,
  right_recording_id text,
  status text,
  created_at timestamptz DEFAULT now()
);
